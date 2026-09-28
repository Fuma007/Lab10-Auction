package se331.lab10auction.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    Double amount;
    String datetime;
    @ManyToOne
    @JoinColumn(name = "auction_item_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    AuctionItem item;
}