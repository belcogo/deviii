import React, { useMemo, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import userService from '../../services/userService';
import { Button, Input } from '../../components';
import { Header } from '../../components/header/header.component';
import './register.style.css'

export const RegisterPage = () => {
  const [name, setName] = useState('');
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');
  const [errors, setErrors] = useState([]);
  const shouldDisableButton = useMemo(() => !name || !email || !password, [name, email, password])
  const navigate = useNavigate();

  const returnToLoing = () => navigate('/login')

  const handleRegister = async (e) => {
    e.preventDefault();
    try {
      await userService.createUser(name, password, email);
      returnToLoing(); // Redirecionar para a página de login após o registro
    } catch (error) {
      console.debug(error?.response?.data)
      setErrors(error.response.data)
    }
  };

  return (
    <div className="container">
      <Header title="CADASTRO" />
      <form onSubmit={handleRegister} className="form pageContent">
        <div className="formGroup">
          <Input
            label="Nome"
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
          <Input
            label="Email"
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <Input
            label="Senha"
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <div className="columnWrapper">
          <Button type="submit" text="Cadastrar" filled error={errors?.length} disabled={shouldDisableButton} />
          <Button type="button" text="Voltar ao Login" outlined error={errors?.length} onPress={returnToLoing} />
        </div>
      </form>
    </div>
  );
};
