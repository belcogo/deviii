import React, { useState } from 'react';
import { Button, Input } from '../../components';
import { useRecoilValue } from "recoil";
import { userAtom } from "../../state/user.atom";
import './profile.style.css'

export const ProfilePage = () => {
    
  const user = useRecoilValue(userAtom)

  const [errors, setErrors] = useState([]);

  const handleAlterRegister = async (e) => {
      e.preventDefault();
      try {
        // ALTERA O CADASTRO DO USUÁRIO
      } catch (error) {
        console.debug(error?.response?.data)
        setErrors(error.response.data)
      }
    };

  return (
    <div className="page">
      <form onSubmit={handleAlterRegister} className="form pageContent">
        <div className="formGroup">           
          <Input
              label="Nome"
              type="text"
              value={user.name}
          />
          <Input
              label="Email"
              type="email"
              value={user.email}
          />
        </div>
        <div className="columnWrapper">
          <Button type="submit" text="Salvar" filled error={errors?.length} />
        </div>
      </form>
    </div>
	);
};
  