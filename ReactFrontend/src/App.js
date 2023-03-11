import './App.css';
import { Route, Routes } from 'react-router-dom';
import CustomerPage from './components/CustomerPage';
import SignUpForm from './components/SignUpForm';
import LandingPage from './components/LandingPage';
import ManufacturerPage from './components/ManufacturerPage';
import LoginForm from './components/LoginForm';
import Profile from './components/Profile';
import NoMatch from './components/NoMatch';
import ForgotPassword from './components/ForgotPassword';
import ResetPass from './components/ResetPass';
import CustomerProfile from './components/CustomerProfile';

function App() {
  return (
    <>
    <Routes>
      <Route path='/' element={<LandingPage />} />
      <Route path='customer' element={<CustomerPage />}>
        <Route index element={<LoginForm />}/>
      </Route>
      <Route path='manufacturer' element={<ManufacturerPage />}>
        <Route index element={<LoginForm />}/>
      </Route>
      <Route path='/register' element={<SignUpForm/>} />
      <Route path='/customerprofile' element={<CustomerProfile />}/>
      <Route path='/manufacturer/profile' element={<Profile />} />
      <Route path='/forgot' element={<ForgotPassword />} />
      <Route path='/signin' element={<LoginForm/>} />
      <Route path='/reset' element={<ResetPass/>} />
      <Route path='*' element={<NoMatch />} />
    </Routes>
    </>
  );
}

export default App;