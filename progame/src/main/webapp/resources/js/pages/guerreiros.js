let j = new Vue({
    el : '#jogador',
    methods : {
        selecionaJogador : function(value){
            const vm = this;
            // Apagar, apenas usar enquanto n tiver banco hospedado
                // sessionStorage.setItem('idPersonagem', value);   
                // sessionStorage.setItem('escolheu', 1);   
                // window.location.href = 'perfil.html';
                // sessionStorage.setItem('imgPersonagem', '../resources/img/jogadores/aisha.png ');
            //
            axios.get('../rs/personagem/setPersonagem/'+value+'/'+sessionStorage.getItem('matricula')).then(function(response) {
                sessionStorage.setItem('idPersonagem', value);   
                sessionStorage.setItem('escolheu', 1);   
                window.location.href = 'perfil.html';
            });
        }
    }
    
 });


function fundoMusical(){
        const audio = document.querySelector('audio');
        audio.play();
}

fundoMusical();




