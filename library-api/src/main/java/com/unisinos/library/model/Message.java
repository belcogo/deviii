package com.unisinos.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;

@Entity
@Table(name="messages")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public String messageSent;

    @Column(nullable = false)
    @ColumnDefault("false")
    public boolean read;

    @ManyToOne
    @JoinColumn(name = "send_id")
    public User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    public User receiver;

    @CreationTimestamp
    @Column(name = "created_date_time", nullable = false, updatable = false)
    public ZonedDateTime createdDateTime;

    @Column(name = "read_date_time", nullable = true)
    public ZonedDateTime readDateTime;

    @ManyToOne
    @JoinColumn(name = "borrow_id")
    @JsonIgnore
    public Borrow borrow;
}
