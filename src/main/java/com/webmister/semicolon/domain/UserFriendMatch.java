package com.webmister.semicolon.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserFriendMatch {

    @Id
    @Column(name = "UserFriendMatchId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long UserFriendMatchId;



}
