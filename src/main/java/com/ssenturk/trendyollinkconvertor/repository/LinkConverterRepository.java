package com.ssenturk.trendyollinkconvertor.repository;

import com.ssenturk.trendyollinkconvertor.entity.ConvertedLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkConverterRepository extends JpaRepository<ConvertedLink, Integer> {
}
