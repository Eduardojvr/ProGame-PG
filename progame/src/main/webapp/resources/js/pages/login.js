let login = new Vue({
    el : '#login',
    data : {
        matricula : '',
        senha : '' 
    },
    methods : {
        logar : function(modalName) {
            const vm = this;
            let obj = {
                matricula: vm.matricula,
                senha: vm.senha
             }

            abreLoad(modalName);
            axios.post('rs/user/login', obj).then(function(response) {
                if(response.status == 200){
                    // sessionStorage.setItem('nomeUsuario', response.data["nomeUsuario"]);
                    // sessionStorage.setItem('matricula', response.data["matricula"]);
                    // sessionStorage.setItem('idTipoPerfil', response.data["idTipoPerfil"]);
                    // sessionStorage.setItem('idPersonagem', response.data["idPersonagem"]);
                    // sessionStorage.setItem('email', response.data["email"]);
                    // sessionStorage.setItem('pontuacao', response.data["pontuacao"]);
                    // sessionStorage.setItem('level', response.data["level"]);
                    
            
                    sessionStorage.setItem('idPersonagem', response.data["idPersonagem"]);

                    //temporario
                    // sessionStorage.setItem('nivel', 1);
                    // alert(sessionStorage.getItem('nivel'));

                    // gambiarra :(
                    sessionStorage.setItem('imgPersonagem', '../resources/img/persona.png');
                    if(sessionStorage.getItem('idPersonagem') != 7) {
                        sessionStorage.setItem('login', 1);
                    }
                    window.location.href = "pages/perfil.html"; 
                } else {
                    alert("Não foi possível realizar o login. Usuário ou senha incorretos!");
                }
                fechaLoad(modalName);
            
            }).catch(function (error){
                alert("Erro ao realizar login :(");
                fechaLoad(modalName);

            });
        },
        logout: function(){
            axios.post('../rs/user/logout').then(function(response) {
                alert("Logout realizado com sucesso!");
                sessionStorage.clear();
                window.location.href = "../index.html";
            }).catch(function (error){
                alert("Erro ao realizar logout!");
            });
        }
    }
});
