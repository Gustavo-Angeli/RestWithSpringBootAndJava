import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import "./style.css";

import api from "../../services/api";

export default function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const history = useHistory();

  async function login(e) {
    e.preventDefault();

    const data = {
      username,
      password,
    };

    try {
      const response = await api.post("auth/signin", data);

      localStorage.setItem("username", username);
      localStorage.setItem("accessToken", response.data.token);
      history.push("swagger");
    } catch (error) {
      alert("Login failed");
    }
  }

  return (
    <div className="caixa__login">
      <section className="caixa__login-input input">
        <form onSubmit={login}>
          <h2>Access your account</h2>
          <div className="caixa__login-input">
            <input
              type="Username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
            />
            <label>Username</label>
          </div>
          <div className="caixa__login-input">
            <input
              type="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
            <label>Password</label>
          </div>

          <button type="submit">Login</button>
        </form>
      </section>
    </div>
  );
}
