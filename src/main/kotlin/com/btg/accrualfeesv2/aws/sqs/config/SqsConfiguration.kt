package com.btg.accrualfeesv2.aws.sqs.config

import com.btg.accrualfeesv2.aws.sqs.listener.SqsListener
import io.awspring.cloud.sqs.listener.ContainerOptions
import io.awspring.cloud.sqs.listener.SqsMessageListenerContainer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sqs.SqsAsyncClient
import software.amazon.awssdk.services.sqs.SqsClient
import java.time.Duration

@Configuration
class SqsConfiguration {
    
    @Primary
    @Bean
    fun sqsAsyncClient(): SqsAsyncClient? {
        return SqsAsyncClient.builder().region(Region.US_EAST_1).build()
    }
    
    @Bean
    fun sqsClient(): SqsClient? {
        return SqsClient.builder()
            .credentialsProvider(DefaultCredentialsProvider.create())
            .region(Region.US_EAST_1)
            .build()
    }
    
    @Bean
    fun sqsMessageListenerContainer(sqsAsyncClient: SqsAsyncClient?): SqsMessageListenerContainer<Any>? {
        return SqsMessageListenerContainer
            .builder<Any>()
            .configure { options: ContainerOptions.Builder ->
                options.maxMessagesPerPoll(
                    5
                ).pollTimeout(Duration.ofSeconds(20))
            }
            .sqsAsyncClient(sqsAsyncClient!!)
            .messageListener { message -> listener().listen(message) }
            .queueNames("FeesReversalPaymentsCallback")
            .build()
    }
    
    @Bean
    fun listener(): SqsListener {
        return SqsListener()
    }
}