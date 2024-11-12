import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { HeaderComponent } from './Components/HeaderComponent'


createRoot(document.getElementById('root')).render(
  <StrictMode>
    <HeaderComponent></HeaderComponent>
  </StrictMode>,
)
