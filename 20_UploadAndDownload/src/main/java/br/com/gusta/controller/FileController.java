package br.com.gusta.controller;

import br.com.gusta.data.vo.v1.*;
import br.com.gusta.services.*;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;
import org.springframework.web.servlet.support.*;

import javax.servlet.http.*;
import java.util.*;
import java.util.logging.*;
import java.util.stream.*;

@RestController
@RequestMapping("/api/file/v1/")
public class FileController {

    private Logger logger = Logger.getLogger(FileController.class.getName());

    @Autowired
    private FileStorageService service;

    @PostMapping("uploadFile")
    public UploadFileResponseVO uploadFile(@RequestParam("file") MultipartFile file) {
        logger.info("Storing file to disk");

        var filename = service.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/file/v1/downloadFile/")
                .path(filename)
                .toUriString();

        return
                new UploadFileResponseVO(
                        filename, fileDownloadUri, file.getContentType(), file.getSize());
    }

    @PostMapping("uploadMultipleFiles")
    public List<UploadFileResponseVO> uploadMultipleFiles(
            @RequestParam("files") MultipartFile[] files) {
        logger.info("Storing files to disk");

        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }

    //MY_file.txt
    @GetMapping("downloadFile/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(
            @PathVariable String filename, HttpServletRequest request) {

        logger.info("Reading a file on disk");

        Resource resource = service.loadFileAsResource(filename);
        String contentType = "";

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (Exception e) {
            logger.info("Could not determine file type!");
        }

        if (contentType.isBlank()) contentType = "application/octet-stream";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}