package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//Two parameters: The type of entity/object the repository refers to, and the datatype of its id
public interface AdRepository extends JpaRepository<Ad,Long> {

    //examples of derived queries
    Ad findByTitle(String title);

    List<Ad> findByOrderByTitle();


    //examples of JPQL/HQL, custom queries
    @Query("select title from Ad where LENGTH(title) < 15")
    List<String> getTheseAds();

    //use nativeQuery to pass in pure SQL syntax
    @Query(nativeQuery = true, value = "SELECT TITLE FROM ADS WHERE LENGTH(title) < 10")
    List<String> getTheseOtherAds();
}
