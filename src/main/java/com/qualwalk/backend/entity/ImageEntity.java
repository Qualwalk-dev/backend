package com.qualwalk.backend.entity;

import lombok.*;

@Data
public class ImageEntity {

    private String imageName;
    private Integer imageSize;
    private byte[] image;
    private String imageContentType;

}
