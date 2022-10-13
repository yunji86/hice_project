package com.greedy.coffee.review.controller;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.coffee.common.Pagenation;
import com.greedy.coffee.common.PagingButtonInfo;
import com.greedy.coffee.file.dto.FileDTO;
import com.greedy.coffee.member.dto.MemberDTO;
import com.greedy.coffee.review.Service.RevBoardService;
import com.greedy.coffee.review.dto.RevBoardDTO;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
@Controller
@RequestMapping("/review")
public class RevBoardController {
	@Value("${image.image-dir}")
	private String IMAGE_DIR;
	private final RevBoardService revBoardService;
	private final MessageSourceAccessor messageSourceAccessor;
	
	public RevBoardController(RevBoardService revBoardService, MessageSourceAccessor messageSourceAccessor) {
		this.revBoardService = revBoardService;
		this.messageSourceAccessor = messageSourceAccessor;
	}
	
	/* 리뷰 게시판 목록 */
	@GetMapping("list")
	public String revBordList(@RequestParam(defaultValue="1") int page,
							  @RequestParam(required=false) String revSearchValue, Model model) {
		
		log.info("[RevBoardCotroller] =======================================");
		log.info(" [revBordList] param page : {} ", page);
		log.info("[revBordList] param searchValue : {}", revSearchValue);
		
		Page<RevBoardDTO> revBoardList = revBoardService.selectRevBoardList(page, revSearchValue);
		
		PagingButtonInfo paging = Pagenation.getPagingButtonInfo(revBoardList);
		
		log.info("[revBordList] revBoardList : {}", revBoardList);
		log.info("[revBordList] paging : {}", paging);
		
		model.addAttribute("revBoardList", revBoardList);
		model.addAttribute("paging", paging);
		
		if(revSearchValue !=null && !revSearchValue.isEmpty()) {
			model.addAttribute("revSearchValue", revSearchValue);
		}
		
		return "review/revList";
	}
	
	@GetMapping("/view")
	public String selectBoardView(Model model, Long revCode) {
		
		log.info("[RevBoardCotroller] =======================================");
		RevBoardDTO revBoard = revBoardService.RevBoardView(revCode);
		log.info("[selectBoardView] revCode : {}", revCode);
		
		model.addAttribute("revBoard", revBoard);
		
		return "review/revView";
	}
	
	@GetMapping("/create")
	public String goCreate(){
		
		return "review/revCreate";
	}
	
	@PostMapping("/create")
	public String createRevBoard(RevBoardDTO revBoard, 
								@AuthenticationPrincipal MemberDTO member, 
								List<MultipartFile> revFile, RedirectAttributes rttr) {
		
		String rootLocation = IMAGE_DIR;

		String fileUploadDirectory = rootLocation + "/upload/review/original";
		String thumbnailDirectory = rootLocation + "/upload/review/thumbnail";

		File directory = new File(fileUploadDirectory);
		File directory2 = new File(thumbnailDirectory);

		log.info("[createPhotoRev] directory : {}", directory2);

		if (!directory.exists() || !directory2.exists()) {
			log.info("[createPhotoRev] 폴더 생성 : {}", directory.mkdirs());
			log.info("[createPhotoRev]  폴더 생성 : {}", directory2.mkdirs());
		}

		List<FileDTO> fileList = new ArrayList<>();
		
		try {
			for (int i = 0; i < revFile.size(); i++) {

				if (revFile.get(i).getSize() > 0) {

					String originalFileName = revFile.get(i).getOriginalFilename();

					log.info("[createPhotoRev] originalFileName : " + originalFileName);

					String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
					String savedFileName = UUID.randomUUID().toString().replace("-", "") + ext;

					log.info("[createPhotoRev] svavedFileName : " + savedFileName);

					revFile.get(i).transferTo(new File(fileUploadDirectory + "/" + savedFileName));

					FileDTO fileInfo = new FileDTO();
					fileInfo.setFileOrgName(originalFileName);
					fileInfo.setFileSaveName(savedFileName);
					fileInfo.setFilePath("/upload/review/original/");

					if (i == 0) {
						fileInfo.setFileType("REVTITLE");
						Thumbnails.of(fileUploadDirectory + "/" + savedFileName).size(300, 300)
								.toFile(thumbnailDirectory + "/thumbnail_" + savedFileName);
						fileInfo.setFileThumPath("/upload/review/thumbnail/thumbnail_" + savedFileName);
					} else {
						fileInfo.setFileType("BODY");
					}
					fileList.add(fileInfo);
				}
			}

			log.info("[createPhotoRev] revFileList :  {} ", fileList);

			revBoard.setFileList(fileList);

			log.info("[createPhotoRev] revBoard :  {}", revBoard);
			
			revBoard.setWriter(member);
			revBoardService.createRevBoard(revBoard);
			
			rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("rev.create"));
			
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();

			for (FileDTO file : fileList) {

				File deleteFile = new File(file.getFilePath() + "/" + file.getFileSaveName());
				deleteFile.delete();

				File deleteThumbnail = new File(thumbnailDirectory + "/thumbnail_" + file.getFileSaveName());
				deleteThumbnail.delete();
			}
		}
		
		return "redirect:/review/list";
		
	} 
	@GetMapping("/edit")
	public String editRevBoard(Long revCode, Model model) {
		
		RevBoardDTO revBoard = revBoardService.RevBoardView(revCode);
		model.addAttribute("revBoard", revBoard);
	
		return "review/revUpdate";
		
	}
	
	@PostMapping("/update")
	public String updateRevBoard(@RequestParam MultipartFile revFile,
			@ModelAttribute RevBoardDTO updateRev, RedirectAttributes rttr) {
		
		log.info("[RevBoardCotroller] =======================================");
		log.info("[updateRevBoard] pdateRev request : {}", updateRev);
		
		
		revBoardService.updateRevBoard(updateRev);
		rttr.addFlashAttribute("Message", messageSourceAccessor.getMessage("rev.update"));
		return "redirect:/review/list";
	}
	
	@PostMapping("/delete")
	public String deleteRev (Long revCode, RedirectAttributes rttr) {
		
		log.info("[deleteRev] revBoard request : {}", revCode);
		
		revBoardService.deleteRev(revCode);
		
		rttr.addFlashAttribute("Message", messageSourceAccessor.getMessage("rev.delete"));
		
		return "redirect:/review/list";
	}
	


}
