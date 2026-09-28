package se331.lab10auction.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import se331.lab10auction.entity.AuctionItem;

import java.util.List;

public interface AuctionItemRepository extends JpaRepository<AuctionItem, Long> {
    List<AuctionItem> findAll();
    Page<AuctionItem> findByDescriptionContainingIgnoreCase(String description, Pageable pageable);
    Page<AuctionItem> findBySuccessfulBid_AmountLessThan(Double amount, Pageable pageable);
    Page<AuctionItem> findByDescriptionContainingIgnoreCaseOrTypeContainingIgnoreCase(String description, String type, Pageable pageable);
}