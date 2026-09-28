package se331.lab10auction.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.lab10auction.entity.AuctionItem;
import se331.lab10auction.repository.AuctionItemRepository;

@Repository
@RequiredArgsConstructor
public class AuctionItemDaoImpl implements AuctionItemDao {
    final AuctionItemRepository auctionItemRepository;

    @Override
    public Page<AuctionItem> getAuctionItems(Integer pageSize, Integer page) {
        pageSize = pageSize == null ? 3 : pageSize;
        page = page == null ? 1 : page;
        return auctionItemRepository.findAll(PageRequest.of(page - 1, pageSize));
    }

    @Override
    public Page<AuctionItem> getAuctionItemsByDescription(String description, Pageable pageable) {
        return auctionItemRepository.findByDescriptionContainingIgnoreCase(description, pageable);
    }

    @Override
    public Page<AuctionItem> getAuctionItemsBySuccessfulBidLessThan(Double amount, Pageable pageable) {
        return auctionItemRepository.findBySuccessfulBid_AmountLessThan(amount, pageable);
    }

    @Override
    public Page<AuctionItem> getAuctionItemsByKeyword(String keyword, Pageable pageable) {
        return auctionItemRepository.findByDescriptionContainingIgnoreCaseOrTypeContainingIgnoreCase(keyword, keyword, pageable);
    }

    @Override
    public AuctionItem getAuctionItem(Long id) {
        return auctionItemRepository.findById(id).orElse(null);
    }

    @Override
    public AuctionItem save(AuctionItem auctionItem) {
        return auctionItemRepository.save(auctionItem);
    }
}