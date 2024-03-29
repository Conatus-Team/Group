//package conatus.config.kafka;
//
//import org.springframework.cloud.stream.annotation.Input;
//import org.springframework.cloud.stream.annotation.Output;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.SubscribableChannel;
//
//public interface KafkaProcessor {
//    String INPUT = "myTopic";
//    String OUTPUT = "myTopic"; // groupTopic
//
//    @Input(KafkaProcessor.INPUT)
//    SubscribableChannel inputChannel();
//
//    @Output(KafkaProcessor.OUTPUT)
//    MessageChannel outputChannel();
//}
