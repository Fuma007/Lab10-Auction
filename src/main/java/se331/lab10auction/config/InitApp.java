package se331.lab10auction.config;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import se331.lab10auction.entity.AuctionItem;
import se331.lab10auction.entity.Bid;
import se331.lab10auction.repository.AuctionItemRepository;
import se331.lab10auction.repository.BidRepository;

@Component
@RequiredArgsConstructor
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    final AuctionItemRepository auctionItemRepository;
    final BidRepository bidRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // Item 1: Vintage Watch - has successful bid
        AuctionItem item1 = auctionItemRepository.save(AuctionItem.builder()
                .description("Vintage Rolex Watch")
                .type("Antique")
                .build());
        Bid b1a = bidRepository.save(Bid.builder().amount(100.0).datetime("2026-09-01 10:00").item(item1).build());
        bidRepository.save(Bid.builder().amount(150.0).datetime("2026-09-01 11:00").item(item1).build());
        Bid b1c = bidRepository.save(Bid.builder().amount(200.0).datetime("2026-09-01 12:00").item(item1).build());
        item1.setSuccessfulBid(b1c);
        auctionItemRepository.save(item1);

        // Item 2: Oil Painting - has successful bid
        AuctionItem item2 = auctionItemRepository.save(AuctionItem.builder()
                .description("Oil Painting Landscape")
                .type("Art")
                .build());
        bidRepository.save(Bid.builder().amount(500.0).datetime("2026-09-02 09:00").item(item2).build());
        bidRepository.save(Bid.builder().amount(600.0).datetime("2026-09-02 10:00").item(item2).build());
        Bid b2c = bidRepository.save(Bid.builder().amount(650.0).datetime("2026-09-02 11:00").item(item2).build());
        item2.setSuccessfulBid(b2c);
        auctionItemRepository.save(item2);

        // Item 3: Acoustic Guitar - has successful bid
        AuctionItem item3 = auctionItemRepository.save(AuctionItem.builder()
                .description("Acoustic Guitar")
                .type("Instrument")
                .build());
        bidRepository.save(Bid.builder().amount(50.0).datetime("2026-09-03 09:00").item(item3).build());
        bidRepository.save(Bid.builder().amount(75.0).datetime("2026-09-03 10:00").item(item3).build());
        Bid b3c = bidRepository.save(Bid.builder().amount(90.0).datetime("2026-09-03 11:00").item(item3).build());
        item3.setSuccessfulBid(b3c);
        auctionItemRepository.save(item3);

        // Item 4: Bronze Sculpture - no successful bid
        AuctionItem item4 = auctionItemRepository.save(AuctionItem.builder()
                .description("Bronze Sculpture")
                .type("Art")
                .build());
        bidRepository.save(Bid.builder().amount(300.0).datetime("2026-09-04 09:00").item(item4).build());
        bidRepository.save(Bid.builder().amount(350.0).datetime("2026-09-04 10:00").item(item4).build());
        bidRepository.save(Bid.builder().amount(400.0).datetime("2026-09-04 11:00").item(item4).build());

        // Item 5: Coin Collection - no successful bid
        AuctionItem item5 = auctionItemRepository.save(AuctionItem.builder()
                .description("Rare Coin Collection")
                .type("Antique")
                .build());
        bidRepository.save(Bid.builder().amount(20.0).datetime("2026-09-05 09:00").item(item5).build());
        bidRepository.save(Bid.builder().amount(25.0).datetime("2026-09-05 10:00").item(item5).build());
        bidRepository.save(Bid.builder().amount(30.0).datetime("2026-09-05 11:00").item(item5).build());
    }
}