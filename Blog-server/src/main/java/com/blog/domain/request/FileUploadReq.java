package com.blog.domain.request;import lombok.Data;/** * @author rhwu * @description * @create 2024/7/11 */@Datapublic class FileUploadReq {    /**     *     */    private String fileName;    /**     * 访问地址     */    private String fileUrl;    /**     * 访问地址的二维码base64     */    private String fileQrcode;}