let usuario = new Vue({
	el : '#cadastro',
    data : {
		nomeUsuario : '',
        matricula : '',
		senha : '',
		idTipoPerfil : '',
		idPersonagem : '',
		email : '',
		mensagens: []
        
	},
	methods : {
		verificaCampos : function (obj){
			const vm = this;
			// for(var i = 0; i < vm.mensagens.length; i++){
			// 	vm.mensagens[i] = null;
			// }
			vm.mensagens = [];
		
			var isOk = 1;
			if(obj.nomeUsuario == ''){
				vm.mensagens.push('* Nome de usuário é obrigatório');
			}
			if(obj.email == ''){
				vm.mensagens.push('* Email é obrigatório');
			} 
			if(obj.matricula == ''){
				vm.mensagens.push('* Matrícula é obrigatória');
			}
			if(obj.senha == ''){
				vm.mensagens.push('* Senha é obrigatória');
			}
			for(var a = 0; a < vm.mensagens.length; a++){
				if(vm.mensagens[a] != null){
					demo.novaMensagem(vm.mensagens[a], 4);
					demo.showNotification();
				}
			}

			if(parseInt(vm.mensagens.length) > 0){
				isOk = 0;
			}
			return isOk;
		},
		cadastra : function(modalName) {
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
			if(vm.verificaCampos(obj) == 1){
				abreLoad(modalName);
				axios.post('rs/user/insert', obj).then(function(response) {
					if(response.status == 200){
						// alert("Usuário cadastrado com sucesso!");
						demo.novaMensagem('Usuário cadastrado com sucesso!', 2);
						demo.showNotification();
						window.location.href = 'login.html';
					} 
				}).catch(function (error){
					demo.novaMensagem('Usuário já cadastrado!', 4);
					demo.showNotification();
				}).finally(function(){
					fechaLoad(modalName);
				});
			}
		}
	}
});

