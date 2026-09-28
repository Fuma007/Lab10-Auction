package se331.lab10auction.util;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import se331.lab10auction.entity.AuctionItem;
import se331.lab10auction.entity.AuctionItemDTO;
import se331.lab10auction.entity.Bid;
import se331.lab10auction.entity.BidDTO;

import java.util.List;

@Mapper
public interface AuctionMapper {
    AuctionMapper INSTANCE = Mappers.getMapper(AuctionMapper.class);

    AuctionItemDTO getAuctionItemDto(AuctionItem auctionItem);
    List<AuctionItemDTO> getAuctionItemDto(List<AuctionItem> auctionItems);

    BidDTO getBidDto(Bid bid);
    List<BidDTO> getBidDto(List<Bid> bids);
}