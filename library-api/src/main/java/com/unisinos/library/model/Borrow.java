package com.unisinos.library.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name= "borrows")
public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "borrow_status")
    @Enumerated(EnumType.STRING)
    public BorrowStatus borrowStatus;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    public User owner;

    @ManyToOne
    @JoinColumn(name = "book_requested_id")
    public Book bookRequested;

    @ManyToOne
    @JoinColumn(name = "requester_id")
    public User requester;

    @CreationTimestamp
    @Column(name = "created_date_time", nullable = false, updatable = false)
    public ZonedDateTime createdDateTime;

    @Column(name = "last_updated_time", nullable = false)
    public ZonedDateTime lastUpdatedTime;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Message> messages;
}
