package com.liaozan.biz.service;

import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.liaozan.common.config.WebApplicationPropertiesConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.List;

/**
 * @author liaozan
 * @version 1.0.0
 * @since 2018/1/14
 */
@Service
@EnableConfigurationProperties(WebApplicationPropertiesConfig.class)
public class FileService {

	@Autowired
	private WebApplicationPropertiesConfig webApplicationPropertiesConfig;

	public List<String> getImgPath(List<MultipartFile> files) {
		List<String> paths = Lists.newArrayList();
		files.forEach(file -> {
			File localFile = null;
			if (!file.isEmpty()) {
				try {
					localFile = saveToLocal(file, webApplicationPropertiesConfig.getSavapath());
				} catch (IOException e) {
					throw new IllegalArgumentException();
				}
				String path = null;
				if (localFile != null) {
					path = StringUtils.substringAfterLast(localFile.getAbsolutePath(), webApplicationPropertiesConfig.getSavapath());
				}
				paths.add(path);
			}
		});
		return paths;
	}

	private File saveToLocal(MultipartFile file, String filePath) throws IOException {
		File newFile = new File(filePath + File.separator + Instant.now().getEpochSecond() + File.separator + file.getOriginalFilename());
		if (!newFile.exists()) {
			newFile.getParentFile().mkdirs();
			newFile.createNewFile();
		}
		Files.write(file.getBytes(), newFile);
		return newFile;
	}
}
