# Spring Data Jpa & Hibernate

## Technologies

* Spring Data JPA
* Hibernate
* H2

## Description

In this repo, you can find the same query `findAll` using different approaches in order to manipulate the fetch plan of the query

## Goal

This repo is used to demonstrate the different behaviours of JPA/Hibernate when loading a single Entity and its relations or a List of Entities and their relations, 
and understand the differences and the defaults

## How to start the application

`mvn spring-boot:run`

## Examples

### findAll using JPA Criteria to enable the fetch join for the relations

`curl http://localhost:8080/user/predicate`

```
Hibernate: select user0_.id as id1_1_0_, userdetail1_.id as id1_21_, contactdet2_.id as id1_0_2_, user0_.contact_details_id as contact_3_1_0_, user0_.name as name2_1_0_, userdetail1_.nationality as national2_2_1_, userdetail1_.user_id as user_id3_2_1_, contactdet2_.email as email2_0_2_, contactdet2_.mobile as mobile3_0_2_ 
from user user0_ 
left outer join user_details userdetail1_ on user0_.id=userdetail1_.user_id 
left outer join contact_details contactdet2_ on user0_.contact_details_id=contactdet2_.id
```

### findAll using NamedEntityGraphs to specify the fetch plan

`curl http://localhost:8080/user/all`

```
Hibernate: select user0_.id as id1_1_0_, contactdet1_.id as id1_0_1_, userdetail2_.id as id1_2_2_, user0_.contact_details_id as contact_3_1_0_, user0_.name as name2_1_0_, contactdet1_.email as email2_0_1_, contactdet1_.mobile as mobile3_0_1_, userdetail2_.nationality as national2_2_2_, userdetail2_.user_id as user_id3_2_2_ 
from user user0_ 
left outer join contact_details contactdet1_ on user0_.contact_details_id=contactdet1_.id 
left outer join user_details userdetail2_ on user0_.id=userdetail2_.user_id
```

... you can also change the name of the EntityGraph that is being used in the `UserRepository` to `@EntityGrah("withUserDetails")` and see the difference

### findAll without any customization 

Go to the `UserRepository` and comment the `@EntityGraph("withAllDetails")` 

```java
    //@EntityGraph("withAllDetails")
    List<User> findAll();
```

Restart the application

`curl http://localhost:8080/user/all`

```
Hibernate: select user0_.id as id1_1_, user0_.contact_details_id as contact_3_1_, user0_.name as name2_1_ from user user0_
Hibernate: select contactdet0_.id as id1_0_0_, contactdet0_.email as email2_0_0_, contactdet0_.mobile as mobile3_0_0_ from contact_details contactdet0_ where contactdet0_.id=?
Hibernate: select userdetail0_.id as id1_2_2_, userdetail0_.nationality as national2_2_2_, userdetail0_.user_id as user_id3_2_2_, user1_.id as id1_1_0_, user1_.contact_details_id as contact_3_1_0_, user1_.name as name2_1_0_, contactdet2_.id as id1_0_1_, contactdet2_.email as email2_0_1_, contactdet2_.mobile as mobile3_0_1_ from user_details userdetail0_ left outer join user user1_ on userdetail0_.user_id=user1_.id left outer join contact_details contactdet2_ on user1_.contact_details_id=contactdet2_.id where userdetail0_.user_id=?
Hibernate: select contactdet0_.id as id1_0_0_, contactdet0_.email as email2_0_0_, contactdet0_.mobile as mobile3_0_0_ from contact_details contactdet0_ where contactdet0_.id=?
Hibernate: select userdetail0_.id as id1_2_2_, userdetail0_.nationality as national2_2_2_, userdetail0_.user_id as user_id3_2_2_, user1_.id as id1_1_0_, user1_.contact_details_id as contact_3_1_0_, user1_.name as name2_1_0_, contactdet2_.id as id1_0_1_, contactdet2_.email as email2_0_1_, contactdet2_.mobile as mobile3_0_1_ from user_details userdetail0_ left outer join user user1_ on userdetail0_.user_id=user1_.id left outer join contact_details contactdet2_ on user1_.contact_details_id=contactdet2_.id where userdetail0_.user_id=?
Hibernate: select userdetail0_.id as id1_2_2_, userdetail0_.nationality as national2_2_2_, userdetail0_.user_id as user_id3_2_2_, user1_.id as id1_1_0_, user1_.contact_details_id as contact_3_1_0_, user1_.name as name2_1_0_, contactdet2_.id as id1_0_1_, contactdet2_.email as email2_0_1_, contactdet2_.mobile as mobile3_0_1_ from user_details userdetail0_ left outer join user user1_ on userdetail0_.user_id=user1_.id left outer join contact_details contactdet2_ on user1_.contact_details_id=contactdet2_.id where userdetail0_.user_id=?
```

