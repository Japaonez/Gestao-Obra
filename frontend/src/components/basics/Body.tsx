import { useState } from "react";


interface BodyProps{
    msg: string;
    msg_esp?: string;
}

const Body: React.FC<BodyProps> = (props) => {
    const [contador, setContador] = useState(1);

    function buttonClick(){
        setContador(contador + 1);
    }

    return (
        <>
        <h1>{ props.msg }</h1>
        <h2>{ contador }</h2>
        <button onClick = {buttonClick}>ATUALIZAR</button>
        </>
    )
}
export default Body;