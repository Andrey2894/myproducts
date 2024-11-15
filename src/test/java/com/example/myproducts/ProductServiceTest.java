package com.example.myproducts;

import com.example.myproducts.bll.ProductService;
import com.example.myproducts.dal.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServiceTest {

    @Mock
    private EntityManager entityManager;
    @Mock
    private CriteriaBuilder criteriaBuilder;
    @Mock
    private CriteriaQuery<Product> criteriaQuery;
    @Mock
    private Root<Product> root;
    @Mock
    private TypedQuery<Product> typedQuery;
    @InjectMocks
    private ProductService productService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Product.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Product.class)).thenReturn(root);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
    }

}


