import './App.css';
import { Route, Routes } from 'react-router-dom';
import CustomerPage from './components/CustomerPage';
import SignUpForm from './components/SignUpForm';
import SignUpFormManufacturer from './components/SignUpFormManufacturer';
import LandingPage from './components/LandingPage';
import ManufacturerPage from './components/ManufacturerPage';
import LoginForm from './components/LoginForm';
import CustomerProfile from './components/CustomerProfile';
import CustomersOfManufacturer from './components/CustomersOfManufacturer';
import ManufacturerProfile from './components/ManufacturerProfile';
import NoMatch from './components/NoMatch';
import ForgotPassword from './components/ForgotPassword';
import ResetPass from './components/ResetPass';
import RequestsOfManufacturer from './components/RequestsOfManufacturer';
import ManufacturerLoginForm from './components/ManufacturerLoginForm';
import EditRequest from './components/EditRequest';


function App() {
  return (
    <>
      <Routes>
        <Route path='/' element={<LandingPage />} />
        <Route path='customer' element={<CustomerPage />}>
          <Route index element={<LoginForm />} />
          <Route path='register' element={<SignUpForm />} />
        </Route>
        <Route path='manufacturer' element={<ManufacturerPage />}>
          <Route index element={<ManufacturerLoginForm />} />
          <Route path='register' element={<SignUpFormManufacturer />} />
        </Route>
        <Route path='/customer/profile' element={<CustomerProfile />} />
        <Route path='/manufacturer/profile' element={<ManufacturerProfile />} />
        <Route path='/forgot' element={<ForgotPassword />} />
        <Route path='/signin' element={<LoginForm />} />
        <Route path='/reset' element={<ResetPass />} />
        <Route path='*' element={<NoMatch />} />
        <Route path='/manufacturer/customers' element={<CustomersOfManufacturer />}></Route>
        <Route path='/manufacturer/requests' element={<RequestsOfManufacturer />}></Route>
        <Route path='/editRequest/:id' element={<EditRequest />}></Route>
      </Routes>
    </>
  );
}

export default App;