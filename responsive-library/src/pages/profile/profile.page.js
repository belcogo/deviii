import React, { useMemo, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import userService from '../../services/userService';
import { Button, Input } from '../../components';
import { useRecoilValue } from "recoil";
import { userAtom } from "../../state/user.atom";
import './profile.style.css'

export const ProfilePage = () => {
    
    const user = useRecoilValue(userAtom)

    const [name, setName] = useState('');
    const [password, setPassword] = useState('');
    const [email, setEmail] = useState('');
    const [errors, setErrors] = useState([]);
    const shouldDisableButton = useMemo(() => !name || !email || !password, [name, email, password])
    const navigate = useNavigate();

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
                    <Button type="submit" text="Salvar" filled error={errors?.length}>

                    </Button>
                </div>


            </form>










        </div>
	);
  


  };
  