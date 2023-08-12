package com.LinkTree.LinktreeClone.Repository;


import com.LinkTree.LinktreeClone.Model.Link;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {

    @Query(value = "SELECT * FROM link WHERE user_id = :userId" ,nativeQuery = true)
    List<Link> getAllLinksByUserId(Long userId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM link WHERE user_id = :userId" ,nativeQuery = true)
    void deleteLinkDataByUserId(Long userId);
}
