import React from 'react'




const Header = () => {



  return (
    <header className='bg-gray-800 mx-auto flex max-w-7xl items-center justify-between p-2 lg:px-6'>
        <div className='h-16 px-8 flex items-center'>
          <p className='text-white font-bold'>FinTune - Stock Monitoring</p>
        </div>
        <div class="hidden lg:flex lg:flex-1 lg:justify-end">
          {/* <div class="flex justify-between lg:justify-start">
          {isAuthenticated &&
            <button
              class="inline-flex items-center justify-between 
              rounded-full bg-indigo-600 px-3 py-2 
              text-sm font-semibold text-white shadow-sm 
              hover:bg-indigo-500 focus-visible:outline 
              focus-visible:outline-2 focus-visible:outline-offset-2 
              focus-visible:outline-indigo-600 lg:ml-6 lg:justify-start"
              role="link"
              onClick={AuthService.signout}
            >
            Logout</button>}
          </div> */}
        </div>
    </header>
    
  );
};

export default Header;