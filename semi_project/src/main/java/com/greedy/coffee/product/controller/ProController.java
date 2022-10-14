package com.greedy.coffee.product.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.coffee.common.Pagenation;
import com.greedy.coffee.common.PagingButtonInfo;
import com.greedy.coffee.file.dto.FileDTO;
import com.greedy.coffee.member.dto.MemberDTO;
import com.greedy.coffee.product.dto.ProDTO;
import com.greedy.coffee.product.entity.Product;
import com.greedy.coffee.product.service.ProService;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
@Controller
@RequestMapping("/product")
public class ProController {
	
	@Value("${image.image-dir}")
	private String IMAGE_DIR;
	
	private final ProService proService;
	private final MessageSourceAccessor messageSourceAccessor;
	
	public ProController(ProService proService, MessageSourceAccessor messageSourceAccessor) {
		this.proService = proService;
		this.messageSourceAccessor = messageSourceAccessor;
	}
	
	//상품 전체 조회
	@GetMapping("proList")
	public String selectAllProductList(@RequestParam(defaultValue = "1") int page, 
				@RequestParam(required = false) String proSearchValue, Model model) {
		
		log.info("[ProController]==========================");
		log.info("[ProController] parameter page{} : " +page);
		
		Page<ProDTO> productList = proService.selectProductList(page, proSearchValue);
		PagingButtonInfo paging = Pagenation.getPagingButtonInfo(productList);
		
		System.out.println("productList에 뭐 들었냐3 " + productList);
		
		log.info("[proController] productList : " + productList);
		log.info("[Procontroller] paging : {} " + paging);
		
		
		model.addAttribute("productList", productList);
		model.addAttribute("paging",paging);
		
		if(proSearchValue !=null && !proSearchValue.isEmpty()) {
			model.addAttribute("proSearchValue", proSearchValue);
		}
		return "product/proList";
	}
	
	//상품 디테일
	@GetMapping("detail/{proCode}")
	public String selectProductDetail(Model model, @PathVariable("proCode") Long proCode) {
		
		log.info("[productController] ========================================= ");
		log.info("[productController] proCode : {}", proCode);
		
		ProDTO product = proService.selectBoardDetail(proCode);
		
		log.info("[productController] product : {}", product);
		
		model.addAttribute("product", product);
		
		log.info("[productController] ========================================= ");
		
		return "product/proDetail";
	}
	
	//상품 등록 페이지(GET)
	@GetMapping("/new")
	public String goRegist() {
		
		return "product/proRegist";
	}
	
	//상품 등록(Post)
	@PostMapping("/newPro")
	public String registPro(ProDTO product, List<MultipartFile> proFile, 
			@AuthenticationPrincipal MemberDTO member, RedirectAttributes rttr) {
		
		String rootLocation = IMAGE_DIR;
		
		String fileUploadDirectory = rootLocation + "/upload/product/original";
		String thumbnailDirectory = rootLocation + "/upload/product/thumbnail";
		
		File directory = new File(fileUploadDirectory);
		File directory2 = new File(thumbnailDirectory);
		
		log.info("[registPro] directory : {}", directory2);
		
		if (!directory.exists() || !directory2.exists()) {
			log.info("[registPro] 폴더 생성 : {}", directory.mkdirs());
			log.info("[registPro]  폴더 생성 : {}", directory2.mkdirs());
		}

		List<FileDTO> fileList = new ArrayList<>();
		
		try {
			for (int i = 0; i < proFile.size(); i++) {

				if (proFile.get(i).getSize() > 0) {

					String originalFileName = proFile.get(i).getOriginalFilename();

					log.info("[registPro] originalFileName : " + originalFileName);

					String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
					String savedFileName = UUID.randomUUID().toString().replace("-", "") + ext;

					log.info("[registPro] savedFileName : " + savedFileName);

					proFile.get(i).transferTo(new File(fileUploadDirectory + "/" + savedFileName));

					FileDTO fileInfo = new FileDTO();
					fileInfo.setFileOrgName(originalFileName);
					fileInfo.setFileSaveName(savedFileName);
					fileInfo.setFilePath("/upload/product/original/");

					if (i == 0) {
						fileInfo.setFileType("PROFILE");
						Thumbnails.of(fileUploadDirectory + "/" + savedFileName).size(300, 300)
								.toFile(thumbnailDirectory + "/thumbnail_" + savedFileName);
						fileInfo.setFileThumPath("/upload/product/thumbnail/thumbnail_" + savedFileName);
					} else {
						fileInfo.setFileType("BODY");
					}
					fileList.add(fileInfo);
				}
			}

			log.info("[registPro] proFileList :  {} ", fileList);

			product.setFileList(fileList);

			log.info("[registPro] product :  {}", product);
			
			product.setWriter(member);
			proService.registProduct(product);
			
			rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("pro.regist"));
			
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();

			for (FileDTO file : fileList) {

				File deleteFile = new File(file.getFilePath() + "/" + file.getFileSaveName());
				deleteFile.delete();

				File deleteThumbnail = new File(thumbnailDirectory + "/thumbnail_" + file.getFileSaveName());
				deleteThumbnail.delete();
			}
		}
		
		return "redirect:/product/proList";
	}
	
	//수정하러가기
	@GetMapping("/modify/{proCode}")
	public String editProduct(@PathVariable Long proCode, Model model) {
		
		ProDTO product = proService.proView(proCode);
		model.addAttribute("product", product);
	
		return "product/proModify";
		
	}
	
	//상품 수정
	@PostMapping("/modify")
	public String modifyProduct(@ModelAttribute ProDTO modifyPro, RedirectAttributes rttr) {
		
		log.info("[productCotroller] =======================================");
		log.info("[modifyProduct] request : {}", modifyPro);
		
		
		proService.modifyPro(modifyPro);
		rttr.addFlashAttribute("modifySuccessMessage", messageSourceAccessor.getMessage("pro.modify"));
		
		return "redirect:/product/proList";
	}
	
	//상품삭제
	@PostMapping("/delete")
	public String deletePro(Long proCode , RedirectAttributes rttr) {
				
		proService.deletePro(proCode);
		log.info("[productCotroller delete] {}", proCode);
		rttr.addFlashAttribute("modifySuccessMessage", messageSourceAccessor.getMessage("pro.delete"));
		
		return "redirect:/product/proList";
	}
	
}
