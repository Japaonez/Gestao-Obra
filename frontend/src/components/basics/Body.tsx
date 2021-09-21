interface BodyProps{
    msg: string;
    msg_esp?: string;
}

const Body: React.FC<BodyProps> = (props) => {
    return (
        <h1>{ props.msg }</h1>
    )
}
export default Body;