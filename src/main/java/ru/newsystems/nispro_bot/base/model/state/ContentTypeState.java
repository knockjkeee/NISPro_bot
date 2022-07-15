package ru.newsystems.nispro_bot.base.model.state;

import lombok.Getter;

@Getter
public enum ContentTypeState {
    BMP("bmp", "image/x-MS-bmp"),
    GIF("gif", "image/gif"),
    JPG("jpg", "image/jpeg"),
    JPEG("jpeg", "image/jpeg"),
    JPE("jpe", "image/jpeg"),
    JFIF("jfif", "image/jpeg"),
    PJPEG("pjpeg", "image/jpeg"),
    PJP("pjp", "image/jpeg"),
    PBM("pbm", "image/x-portable-bitmap"),
    PCD("pcd", "image/x-photo-cd"),
    PIC("pic", "image/x-pict"),
    PNG("png", "image/x-png"),
    TIF("tif", "image/tiff"),
    TIFF("tiff", "image/tiff"),
    NONE("", "");

    private String name;
    private String content;

    ContentTypeState(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public static ContentTypeState getState(String value) {
        return switch (value) {
            case "bmp" -> BMP;
            case "gif" -> GIF;
            case "jpg" -> JPG;
            case "jpeg" -> JPEG;
            case "jpe" -> JPE;
            case "jfif" -> JFIF;
            case "pjpeg" -> PJPEG;
            case "pjp" -> PJP;
            case "pbm" -> PBM;
            case "pcd" -> PCD;
            case "pic" -> PIC;
            case "png" -> PNG;
            case "tif" -> TIF;
            case "tiff" -> TIFF;
            default -> NONE;
        };
    }

}
