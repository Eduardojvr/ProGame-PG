let login = new Vue({
    el : '#login',
    data : {
        matricula : '',
		senha : '' 
    },
    methods : {
        logar : function() {
            const vm = this;
            let obj = {
                matricula: vm.matricula,
                senha: vm.senha
             }
            
            axios.post('/progame/rs/user/login', obj).then(function(response) {
                console.log(response);
                sessionStorage.setItem('isLogado', true);	
                alert("Login realizado com sucesso!");
                window.location.href = "pages/perfil.html";
            }).catch(function (error){
                sessionStorage.setItem('isLogado', false);
                alert("Erro ao realizar login");
            });
        },
        logout: function(){
            axios.post('/progame/rs/user/logout').then(function(response) {
                console.log(response);
                alert("Logout realizado com sucesso!");
                window.location.href = "../index.html";
            }).catch(function (error){
                alert("Erro ao realizar logout!");
            });
        }
    }
});
