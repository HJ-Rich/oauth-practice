function Home() {
    const accessToken = localStorage.getItem("accessToken");
    if (accessToken != null) {
        fetch("http://localhost:8080/api/members")
            .then(result => result.json())
            .then(json => setMemberInfo(json));
    }

    return (
        <div className="App">
            <div id="userInfoWrapper">
                <div id="memberId"></div>
                <div id="memberName"></div>
                <div id="memberEmail"></div>
                <div id="memberImageUrl"></div>
            </div>
            <header className="App-header">
                <div className="container unauthenticated">
                    With GitHub: <a
                    href="https://github.com/login/oauth/authorize?client_id=9e916c5d840731b226a8">깃헙로그인하기</a>
                </div>
            </header>
        </div>
    );
}

function setMemberInfo(memberInfo) {
    document.getElementById('memberId').textContent = memberInfo.id;
    document.getElementById('memberName').textContent = memberInfo.name;
    document.getElementById('memberEmail').textContent = memberInfo.email;
    document.getElementById('memberImageUrl').textContent = memberInfo.imageUrl;

}

export default Home;
