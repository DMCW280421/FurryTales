import { Container, Carousel, Card, Button, Row, Col } from 'react-bootstrap';
import carousal1 from '../images/carousal1.jpg'
import carousal6 from '../images/carousal6.webp'
import carousal3 from '../images/carousal3.png'
import carousal4 from '../images/carousal4.webp'
import carousal6_1 from '../images/carousal6_1.png'
import product1 from '../images/product1.jpg'
import product2 from '../images/product2.webp'
import product3 from '../images/product3.jpg'
import product4 from '../images/product4.jpg'
import product5 from '../images/product5.jpg'
import product6 from '../images/product6.webp'
import product7 from '../images/product7.webp'
import product8 from '../images/product8.webp'
import product6_1 from '../images/product6_1.jpg'
import review2 from '../images/review2.jpg'
import review3 from '../images/review3.jpg'
import review5 from '../images/review5.jpg'
import review6 from '../images/review6.jpg'
import ProductList from './ProductList';
import '../CSS/navbar.css'
import Sidebar from '../components/Sidebar';

function HomePage(){
    return(
        <>
        <Sidebar/>
           <div>
           
           <div id="carouselExampleControls" className="carousel slide" data-bs-ride="carousel">
  <div className="carousel-inner">
    <div className="carousel-item active">
      <img src={carousal1} className="d-block w-100" alt="Slide 1" />
    </div>
    <div className="carousel-item">
      <img src={carousal6} className="d-block w-100" alt="Slide 2" />
    </div>
    <div className="carousel-item">
      <img src={carousal3} className="d-block w-100" alt="Slide 3" />
    </div>
    <div className="carousel-item">
      <img src={carousal4} className="d-block w-100" alt="Slide 4" />
    </div>
    <div className="carousel-item">
      <img src={carousal6_1} className="d-block w-100" alt="Slide 5" />
    </div>
   
  </div>
  <button className="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
    <span className="carousel-control-prev-icon" aria-hidden="true"></span>
    <span className="visually-hidden">Previous</span>
  </button>
  <button className="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
    <span className="carousel-control-next-icon" aria-hidden="true"></span>
    <span className="visually-hidden">Next</span>
  </button>
</div>
<br/><br/><br/>
              
    <div className="container mt-4">
      <div className="row">
        <div className="col">
          <h4 className="fw-bold text-uppercase">Our Bestselling Products</h4>
        </div>
      </div>
    </div>
    <br/><br/><br/>

                 
                  <div className="container mt-4">
      <div className="row">
        <div className="col-md-3">
          <div className="card">
            <img src={product1} className="card-img-top" alt="Card 1" />
            <div className="card-body">
              <h5 className="card-title">Pedigree</h5>
              <p className="card-text">Dog Food</p>
              <a href="ProductList" className="btn btn-primary">Explore</a>
            </div>
          </div>
        </div>
        <div className="col-md-3">
          <div className="card">
            <img src={product2} className="card-img-top" alt="Card 2" />
            <div className="card-body">
              <h5 className="card-title">Drools</h5>
              <p className="card-text">Cat Food</p>
              <a href="ProductList" className="btn btn-primary">Explore</a>
            </div>
          </div>
        </div>
        <div className="col-md-3">
          <div className="card">
            <img src={product3} className="card-img-top" alt="Card 3" />
            <div className="card-body">
              <h5 className="card-title">Pedigree</h5>
              <p className="card-text">Fish Food</p>
              <a href="ProductList" className="btn btn-primary">Explore</a>
            </div>
          </div>
        </div>
        <div className="col-md-3">
          <div className="card">
            <img src={product4} className="card-img-top" alt="Card 4" />
            <div className="card-body">
              <h5 className="card-title">NFS</h5>
              <p className="card-text">Bird Food</p>
              <a href="ProductList" className="btn btn-primary">Explore</a>
            </div>
          </div>
        </div>
        </div>
        <br/><br/><br/><br/><br/><br/><br/><br/>
        <div className="container mt-4">
      <div className="row">
        <div className="col-md-3">
          <div className="card">
            <img src={product5} className="card-img-top" alt="Card 5" />
            <div className="card-body">
              <h5 className="card-title">Himalaya</h5>
              <p className="card-text">Healthy Dog Food</p>
              <a href="ProductList" className="btn btn-primary">Explore</a>
              </div>
          </div>
      </div>
      <div className="col-md-3">
          <div className="card">
            <img src={product6} className="card-img-top" alt="Card 6" />
            <div className="card-body">
              <h5 className="card-title">Bark Couture</h5>
              <p className="card-text">Dog Accessories</p>
              <a href="ProductList" className="btn btn-primary">Explore</a>
              </div>
          </div>
      </div>
      <div className="col-md-3">
          <div className="card">
            <img src={product7} className="card-img-top" alt="Card 6" />
            <div className="card-body">
              <h5 className="card-title">Bark Couture</h5>
              <p className="card-text">Dog Scarf</p>
              <a href="ProductList" className="btn btn-primary">Explore</a>
              </div>
          </div>
      </div>
      <div className="col-md-3">
          <div className="card">
            <img src={product8} className="card-img-top" alt="Card 6" />
            <div className="card-body">
              <h5 className="card-title">Gucci</h5>
              <p className="card-text">Pet Carrier Bag</p>
              <a href="ProductList" className="btn btn-primary">Explore</a>
              </div>
          </div>
      </div>
</div>
     </div>
     <br/><br/><br/><br/><br/><br/><br/>
                 
    <div className="container mt-4">
      <div className="row">
        <div className="col">
          <h4 className="fw-bold text-uppercase">Look at what our Customers have to say about us</h4>
        </div>
      </div>
    </div>
        <div className="container">
        <div className="carousel slide" data-bs-ride="carousel" id="carouselExampleSlidesOnly">
          <div className="carousel-inner">
            <div className="carousel-item active">
              <div className="row">
                <div className="col-lg-4">
                  <div className="card">
                    <div className="box front">
                      <img alt="" src={review2} />
                      <h2>Jessica Doe</h2>
                      <h4>Dog Parent</h4>
                      <p className="socials">
                        <i className="fa fa-facebook"></i>
                        <i className="fa fa-twitter"></i>
                        <i className="fa fa-linkedin"></i>
                        <i className="fa fa-youtube"></i>
                      </p>
                    </div>
                    <div className="box back">
                      <span className="fa fa-quote-left"></span>
                      <p>
                        "My dog absolutely loves Pedigree! Not only does it keep him healthy and energized, but he also enjoys every bite. Highly recommend it!"
                        "Pedigree has been a game-changer for my furry friend. His coat is shinier, his energy levels are up, and he seems happier overall. Thank you for such a great product!"
                      </p>
                    </div>
                  </div>
                </div>
                <div className="col-lg-4">
                  <div className="card">
                    <div className="box front">
                      <img alt="" src={review3} />
                      <h2>Claire Chedd</h2>
                      <h4>Cat Parent</h4>
                      <p className="socials">
                        <i className="fa fa-facebook"></i>
                        <i className="fa fa-twitter"></i>
                        <i className="fa fa-linkedin"></i>
                        <i className="fa fa-youtube"></i>
                      </p>
                    </div>
                    <div className="box back">
                      <span className="fa fa-quote-left"></span>
                      <p>
                        "My cat is a picky eater, but Drools has been a hit from day one! She devours every meal and her coat has never looked better. Thank you for making such delicious and nutritious food for our feline friends!"
                        "Switching to Drools was the best decision I made for my cat. Not only does she love the taste, but I've also noticed improvements in her digestion and overall health. Highly recommended!"
                      </p>
                    </div>
                  </div>
                </div>
                <div className="col-lg-4">
                  <div className="card">
                    <div className="box front">
                      <img alt="" src={review5} />
                      <h2>John Smith</h2>
                      <h4>Owner of FurryTales</h4>
                      <p className="socials">
                        <i className="fa fa-facebook"></i>
                        <i className="fa fa-twitter"></i>
                        <i className="fa fa-linkedin"></i>
                        <i className="fa fa-youtube"></i>
                      </p>
                    </div>
                    <div className="box back">
                      <span className="fa fa-quote-left"></span>
                      <p>
                        "I recently purchased a pet carrier bag from FurryTales, and I'm extremely impressed with the quality! It's durable, comfortable to carry, and my pet seems to enjoy traveling in it. Thank you for providing such stylish and practical accessories for pets!"
                      </p>
                    </div>
                  </div>
                </div>
                <div className="col-lg-4">
                  <div className="card">
                    <div className="box front">
                      <img alt="" src={review6} />
                      <h2>Sarah Johnson</h2>
                      <h4>Avian Enthusiast</h4>
                      <p className="socials">
                        <i className="fa fa-facebook"></i>
                        <i className="fa fa-twitter"></i>
                        <i className="fa fa-linkedin"></i>
                        <i className="fa fa-youtube"></i>
                      </p>
                    </div>
                    <div className="box back">
                      <span className="fa fa-quote-left"></span>
                      <p>
                        "My birds can't get enough of this food! They gobble it up every time I refill their feeder. I've noticed improvements in their plumage and overall health since switching to this brand. Thank you for keeping my feathered friends happy and healthy!"
                        "I've tried several bird foods in the past, but this one is by far the best! My birds love it, and I love that it's made with high-quality ingredients. Highly recommend it to all bird owners!"
                      </p>
                    </div>
                  </div>
                </div>
                </div>
                </div>
                </div></div>
                </div>
                </div>
           </div>
        </>
    )
}

export default HomePage