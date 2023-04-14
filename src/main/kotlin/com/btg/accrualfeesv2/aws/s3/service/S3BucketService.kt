package com.btg.accrualfeesv2.aws.s3.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.*
import software.amazon.awssdk.services.s3.presigner.S3Presigner
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class S3BucketService(
    private val s3Client: S3Client,
    private val s3Presigner: S3Presigner,
    @Value("\${aws.s3.bucket.name}")
    private val nomeBucket: String
) {
    
    fun upload(file: MultipartFile): String {
        try {
            val localdate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
            val fileKey = "${localdate}/${file.originalFilename}"
            
            val putObj = PutObjectRequest.builder()
                .bucket(nomeBucket)
                .key(fileKey)
                .build()
            s3Client.putObject(putObj, RequestBody.fromBytes(file.bytes))
            return fileKey
        } catch (ex: Exception) {
            throw RuntimeException("Erro no Upload do arquivo! Error = ${ex.message}")
        }
    }
    
    fun getPresignedUrl(keyName: String?): String {
        try {
            val getObjectRequest = GetObjectRequest.builder()
                .bucket(nomeBucket)
                .key(keyName)
                .build()
            val getObjectPresignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofSeconds(30))
                .getObjectRequest(getObjectRequest)
                .build()
            val presignedGetObjectRequest: PresignedGetObjectRequest =
                s3Presigner.presignGetObject(getObjectPresignRequest)
            val theUrl = presignedGetObjectRequest.url().toString()
            println("Presigned URL: $theUrl")
            return theUrl
        } catch (ex: S3Exception) {
            throw RuntimeException("Erro para buscar o Presigned Url! Error = ${ex.message}")
        }
    }
}