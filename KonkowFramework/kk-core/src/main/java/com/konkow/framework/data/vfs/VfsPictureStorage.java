package com.konkow.framework.data.vfs;

import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.konkow.framework.FrameworkProperties;

public class VfsPictureStorage {

    private static final Logger LOGGER = LogManager.getLogger(VfsPictureStorage.class);
    private FileSystemOptions fileSystemOptions;
    private FileSystemManager fileSystemManager;
    private static String baseDir;

    static {
        baseDir = FrameworkProperties.getVfsStorageBaseDir();
    }

    public void setFileSystemManager(FileSystemManager fileSystemManager) {
        this.fileSystemManager = fileSystemManager;
    }

    public byte[] load(String filename) {
        FileObject fileObject = VfsUtils.resolveFile(getFilePath(filename), fileSystemManager, fileSystemOptions);
        try {
            return IOUtils.toByteArray(fileObject.getContent().getInputStream());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                fileObject.close();
            } catch (FileSystemException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return new byte[0];
    }

    public void store(String filename, byte[] content) {
        write(filename, content);
    }

    private void write(String filename, byte[] content) {
        try {
            FileObject fileObject = getFileObject(filename);
            OutputStream os = fileObject.getContent().getOutputStream();
            try {
                os.write(content);
            } finally {
                os.close();
                fileObject.close();
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void update(String filename, byte[] content) {
        write(filename, content);
    }

    private FileObject getFileObject(String filename) throws FileSystemException {
        return VfsUtils.resolveFile(getFilePath(filename), fileSystemManager, fileSystemOptions);
    }

    public void remove(String filename) {
        try {
            FileObject fileObject = getFileObject(filename);
            try {
                fileObject.delete();
            } finally {
                fileObject.close();
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = VfsUtils.normalizeBasePath(baseDir);
    }

    protected String getFilePath(String filename) {
        return baseDir + filename;
    }

    private String getFilename(String uuid, String filename) {
        return String.format("%d_%s", uuid, filename);
    }

}
