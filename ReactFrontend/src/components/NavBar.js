import { NavLink } from "react-router-dom"

const NavBar = () => {
    return (
        <div className="container-fluid">

            <nav>
                <nav id="navbar-example2" className="navbar navbar-light bg-light">
                    <ul className="nav nav-pills">
                        <li className="nav-item">
                            <NavLink className="nav-link" to="/">Home</NavLink>
                        </li>
                        <li className="nav-item">
                            <NavLink className="nav-link" to="/customer">Customer</NavLink>
                        </li>
                        <li className="nav-item">
                            <NavLink className="nav-link" to="/manufacturer">Manufacturer</NavLink>
                        </li>
                    </ul>
                </nav>
            </nav>
        </div>
    )
}

export default NavBar;