package com.konkow.framework.data.vfs;

import java.io.File;
import java.io.IOException;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utilities for working with Apache's virtual file system.
 *
 * @author Siriphat Oumtrakul
 */
public final class VfsUtils {
    
    private static final Logger LOGGER = LogManager.getLogger(VfsUtils.class);
	private VfsUtils() {
	}

	/**
	 * Resolve file.
	 */
	public static FileObject resolveFile(String path, FileSystemManager manager, FileSystemOptions options) {
		try {
			if (options == null) {
				return manager.resolveFile(path);
			} else {
				return manager.resolveFile(path, options);
			}
		} catch (FileSystemException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * Normalizes base path.
	 */
	public static String normalizeBasePath(String basePath) {
		basePath.trim();

		if (!basePath.contains("://")) {
			basePath = "file://" + basePath;
		}

		if (basePath.startsWith("file://")) {
			try {
				basePath = "file://" + new File(basePath.substring(7)).getCanonicalPath();
			} catch (IOException e) {
			    LOGGER.error(e.getMessage(), e);
			}
		}

		if (!basePath.endsWith("/") && !basePath.endsWith("\\")) {
			basePath += "/";
		}

		return basePath;
	}
}
