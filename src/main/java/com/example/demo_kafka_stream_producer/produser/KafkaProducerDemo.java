//package com.example.demo_kafka_stream_producer.produser;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.clients.producer.KafkaProducer;
//import org.apache.kafka.clients.producer.ProducerConfig;
//
//import java.util.Properties;
//
//@Slf4j
//public class KafkaProducerDemo implements Runnable {
//
////    private static final Logger LOGGER = Logger.getLogger(Producer.class.getName());
//    private static final String TOPIC_NAME = "cpu-metrics-topic";
//    private KafkaProducerDemo<String, String> kafkaProducer = null;
//    private final String KAFKA_CLUSTER_ENV_VAR_NAME = "KAFKA_CLUSTER";
//
//    public KafkaProducerDemo() {
//        log.info("Kafka Producer running in thread {0}", Thread.currentThread().getName());
//        Properties kafkaProps = new Properties();
//
//        String defaultClusterValue = "localhost:9092";
//        String kafkaCluster = System.getProperty(KAFKA_CLUSTER_ENV_VAR_NAME, defaultClusterValue);
//        log.info("Kafka cluster {0}", kafkaCluster);
//
//        kafkaProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaCluster);
//        kafkaProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
//        kafkaProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
//        kafkaProps.put(ProducerConfig.ACKS_CONFIG, "0");
//
//        this.kafkaProducer = new KafkaProducer(kafkaProps);
//
//    }
//
//    @Override
//    public void run() {
//        try {
//            produce();
//        } catch (Exception e) {
//            log.debug(e.getMessage(), e);
//        }
//    }
//
//    /**
//     * produce messages
//     *
//     * @throws Exception
//     */
//    private void produce() throws Exception {
//        ProducerRecord<String, String> record = null;
//
//        try {
//            Random rnd = new Random();
//            while (true) {
//
//                for (int i = 1; i <= 10; i++) {
//                    String key = "machine-" + i;
//                    String value = String.valueOf(rnd.nextInt(20));
//                    record = new ProducerRecord<>(TOPIC_NAME, key, value);
//
//                    kafkaProducer.send(record, new Callback() {
//                        @Override
//                        public void onCompletion(RecordMetadata rm, Exception excptn) {
//                            if (excptn != null) {
//                                LOGGER.log(Level.WARNING, "Error sending message with key {0}\n{1}", new Object[]{key, excptn.getMessage()});
//                            } else {
//                                LOGGER.log(Level.INFO, "Partition for key-value {0}::{1} is {2}", new Object[]{key, value, rm.partition()});
//                            }
//
//                        }
//                    });
//                    /**
//                     * wait before sending next message. this has been done on
//                     * purpose
//                     */
//                    Thread.sleep(1000);
//                }
//
//            }
//        } catch (Exception e) {
//            LOGGER.log(Level.SEVERE, "Producer thread was interrupted");
//        } finally {
//            kafkaProducer.close();
//
//            LOGGER.log(Level.INFO, "Producer closed");
//        }
//
//    }
//
//}