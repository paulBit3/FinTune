import React, { useContext } from 'react'

import ThemContext from './context/ThemContext'




const ThemCard = ({children}) => {
    const { darkMode } = useContext(ThemContext);
    const { lightMode } = useContext(ThemContext)


  return (
    <div
        className={`w-full h-full rounded-md relative p-8 border-2 ${
            darkMode ? "bg-gray-900 border-gray-800" : "bg-white border-neutral-200"
        }`}
    >
        {children}
    </div>
  );
};

export default ThemCard;