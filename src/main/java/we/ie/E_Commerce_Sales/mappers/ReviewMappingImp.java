package we.ie.E_Commerce_Sales.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import we.ie.E_Commerce_Sales.dtos.ReviewDTO;
import we.ie.E_Commerce_Sales.entities.Review;

@Service
public class ReviewMappingImp {
   public ReviewDTO fromReview(Review review){
        ReviewDTO ReviewDTO = new ReviewDTO();
        BeanUtils.copyProperties(review, ReviewDTO);
        return  ReviewDTO;
    }
    
    public Review fromReviewDTO(ReviewDTO reviewDTO){
        Review review = new Review();
        BeanUtils.copyProperties(reviewDTO,review);
        return  review;
    }
}
