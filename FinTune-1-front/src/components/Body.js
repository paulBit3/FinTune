import React from 'react';
//import "../assets/style.css";


const Body = () => {
  return (
    <>
      <section className='container mx-auto px-6 p-10'>
        <h2 className='text-4xl font-bold text-center text-gray-800 mb-8'>
          Building your personal Portfolio
        </h2>
        <div className='flex items-center flex-wrap mb-20'>
          <div className='w-full md:w-1/2'>
            <h4 className='text-3xl text-gray-800 font-bold mb-3'>
              Stoks
            </h4>
            <p className='text-gray-600 mb-8'>
              Our Stocks Monitoring and Analyst app is able to trade exchange-listed domestic U.S. stocks,
              international stocks via ADR, fractional share, OTC stocks.
            </p>
            <a href='#' className='px-4 py-1 text-sm text-indigo-600 font-semibold rounded-full border border-purple-200 hover:text-indigo-700 hover:bg-indigo-200 focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2'>Learn more <i class="fa fa-chevron-circle-right" aria-hidden="true"></i></a>
          </div>
          <div className='w-full md:w-1/2'>
            <img src='../assets/bg.png' alt='Portfolio' />
          </div>
        </div>
        <div className='flex items-center flex-wrap mb-20'>
          <div className='w-full md:w-1/2'>
            <img src='../assets/bg_142521.png' alt='USMarketProgress' />
          </div>
          <div className='w-full md:w-1/2 pl-10'>
            <h4 className='text-3xl text-gray-800 font-bold mb-3'>
              US Markets
            </h4>
            <p className='text-gray-600 mb-8'>
              FinTune monitores the US Markets to provide you Intra Day, Weekly, Monthly and Yearly stocks data,
              and all new WatchList!
            </p>
            <button
              className='bg-white font-bold rounded-full py-3 px-4 shadow-lg uppercase tracking-wider'>
              Log in to track live stock price<i class="fa fa-chevron-circle-right" aria-hidden="true"></i>
            </button>
          </div>
        </div>
        <div className='flex items-center flex-wrap mb-20'>
          <div className='w-full md:w-1/2'>
            <h4 className='text-3xl text-gray-800 font-bold mb-3'>
              Industries
            </h4>
            <p className='text-gray-600 mb-8'>
              Our Stocks Monitoring and Analyst app allows you to see future industries trends, and buy future industries.
            </p>
          </div>
          <div className='w-full md:w-1/2'>
            <img src='../assets/future_industries_55933.png' />
          </div>
        </div>

      </section>


      <div class="container back-image" id="pricing">
        <div class="row price-background-color">
          <div class="col-lg-12">
            <h2 class="section-heading">Pick your plan.</h2>
            <p>Simple and affordable price plans for you</p>
          </div>
          <div class="col-lg-3 col-md-6">
            <div class="card price-card text-center price-active">
              <div class="card-body">
                <div class="h5 mt-0">Flex</div>
                <div class="price-section">
                  <h2 class="price-text-indigo">$1 / hour</h2>
                </div>
                <ul class="list-unstyled text-muted">
                  <li class="pt-3">5 Companies Per Day</li>
                  <li class="pt-3">5 Stock prices Per Day</li>
                  <li class="pt-3">limited App Usage</li>
                  <li class="pt-3"></li>
                </ul>
              </div>
              <div class="card-footer">
                <p><a href="trial.html" class="text-muted text-small">7 days Free Trial</a></p>
                <a class="btn btn-outline-primary btn-rounded rounded-pill mb-4 waves-effect waves-light" href="/register">SIGN UP</a>
              </div>
            </div>
          </div>
          <div class="col-lg-3 col-md-6">
            <div class="card price-card price-active text-center">
              <div class="card-body">
                <div class="h5 mt-0">Starter</div>
                <div class="price-section bg-gradient text-white">
                  <h2 class="price-text-secondary">$5 / hour</h2>
                </div>
                <ul class="list-unstyled text-muted">
                  <li class="pt-3">Priority listing</li>
                  <li class="pt-3">15 Companies Per Day</li>
                  <li class="pt-3">15 Stock prices Per Day</li>
                  <li class="pt-3">Buy up to 3 Stocks per day</li>
                  <li class="pt-3">Unlimited App Usage</li>
                </ul>
              </div>
              <div class="card-footer">
                <p><a href="trial.html" class="text-muted text-small">7 days Free Trial</a></p>
                <a class="btn btn-secondary btn-rounded bg-gradient rounded-pill mb-4 waves-effect waves-light" href="/register">SIGN UP</a>
              </div>
            </div>
          </div>
          <div class="col-lg-3 col-md-6">
            <div class="card price-card text-center">
              <div class="card-body">
                <div class="h5 mt-0">Premium</div>
                <div class="price-section">
                  <h2 class="price-text-indigo">$8 / hour</h2>
                </div>
                <ul class="list-unstyled text-muted">
                  <li class="pt-3">Priority listing</li>
                  <li class="pt-3">50 Companies Per Day</li>
                  <li class="pt-3">50 Stock Prices Per Day</li>
                  <li class="pt-3">Buy up to 15 Stocks per day</li>
                  <li class="pt-3">Invest your money</li>
                  <li class="pt-3">Unlimited App Usage</li>
                </ul>
              </div>
              <div class="card-footer">
                <p><a href="trial.html" class="text-muted text-small">7 days Free Trial</a></p>
                <a class="btn btn-info btn-rounded rounded-pill mb-4 waves-effect waves-light" href="/register">SIGN UP</a>
              </div>
            </div>
          </div>
          <div class="col-lg-3 col-md-6">
              <div class="card price-card text-center">
                <div class="card-body">
                  <div class="h5 mt-0">Enterprise</div>
                  <div class="price-section">
                    <h2 class="price-text-primary">$10 / hour</h2>
                  </div>
                  <ul class="list-unstyled text-muted">
                    <li class="pt-3">Enterprise package</li>
                    <li class="pt-3">Unlimited Companies</li>
                    <li class="pt-3">Priority listing</li>
                    <li class="pt-3">Unlimited Stock prices</li>
                    <li class="pt-3">Buy & Sell up to 100 Stock daily</li>
                    <li class="pt-3">Invest in Unlimited Companies</li>
                    <li class="pt-3">Unlimited App Usage</li>
                  </ul>
                </div>
                <div class="card-footer">
                  <p><a href="trial.html" class="text-muted text-small">7 days Free Trial</a></p>
                  <a class="btn btn-primary btn-rounded rounded-pill mb-4 waves-effect waves-light" href="/register">SIGN UP</a>
                </div>
              </div>
            </div>
        </div>
      </div>


      <section className='bg-indigo-400'>
        <div className='container mx-auto px-6 text-center py-20'>
          <h2 className='mb-6 text-4xl font-bold text-center text-white'>
            Invest today!
          </h2>
          <h3 className='my-4 text-2xl text-white'>
            Get FinTune, and start monitoring stocks!
          </h3>
          <button
            className='bg-white font-bold rounded-full mt-6 py-2 px-4 shadow-lg uppercase tracking-tighter'
          >
            Start investing today!
          </button>
        </div>
      </section>
    </>
    
  )
}

export default Body;