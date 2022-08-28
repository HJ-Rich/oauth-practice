import {useSearchParams} from "react-router-dom";

function OAuthSlackCallback() {

    const [searchParams, setSearchParams] = useSearchParams();
    const code = searchParams.get("code");
    console.log(code);

    fetch(`http://localhost:8080/login/github?code=${code}`)
        .then(result => result.text())
        .then(text => localStorage.setItem("accessToken", text))
        .then(() => window.location.href = '/');


    return (
        <div>gd</div>
    );
}

export default OAuthSlackCallback;
