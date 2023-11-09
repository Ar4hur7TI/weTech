package com.hex.wetech.service.impl;

import com.hex.wetech.service.IFileService;
import com.hex.wetech.utils.FileUtils;
import org.springframework.stereotype.Service;

/**
 * FileServiceImpl
 *
 * @author Guofeng Lin
 * @since 2023/10/20
 */
@Service
public class FileServiceImpl implements IFileService {
    @Override
    public String createEventFile(String userId, String Id) {
        return FileUtils.initEventFile(userId, Id);
    }
}
