let usuario = new Vue({
	el : '#cadastro',
    data : {
		nomeUsuario : '',
        matricula : '',
		senha : '',
		idTipoPerfil : '',
		idPersonagem : '',
		email : ''
        
    },
	methods : {
		cadastra : function(modalName) {
			abreLoad(modalName);
	    	const vm = this;
            let obj = {
                nomeUsuario : vm.nomeUsuario,
                matricula : vm.matricula,
                senha : vm.senha,
                idTipoPerfil : '3',
                idPersonagem : null,
				email : vm.email,
				pontuacao : '0',
				level : '1'
            }
			axios.post('rs/user/insert', obj).then(function(response) {
				if(response.status == 200){
					alert("Usuário cadastrado com sucesso!");
					window.location.href = 'login.html';
				} 
				fechaLoad(modalName);
			}).catch(function (error){
				alert("Erro, não foi possível cadastrar usuário!");
				fechaLoad(modalName);
            });
		}
	}
});
