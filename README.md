# Spring Boot Kafka Mikro-servis Projesi

Bu projede, Spring Boot ve Apache Kafka'nın birleşimini kullanarak e-ticaret uygulamasının temel işlevlerini gerçekleştirmek için mikro-servis tabanlı bir mimariyi uygulamaktayız. Proje, Shipping, Inventory ve Order adında üç ana mikro-servis içerir ve bu servisler, verileri birbirleriyle senkronize etmek ve gerçek zamanlı işlemleri kolaylaştırmak için Kafka mesajlaşma sistemini kullanır.

Order ve Inventory servisleri OpenFeign ile haberleşmektedir.

## Kullanılan Teknolojiler

- Java 17
- Spring Boot 3x
- Apache Kafka
- Spring Cloud 

