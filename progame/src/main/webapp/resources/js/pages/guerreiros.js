let j = new Vue({
    el : '#jogador',
    methods : {
        selecionaJogador : function(value){
            const vm = this;
            axios.get('/progame/rs/personagem/setPersonagem/'+value+'/'+sessionStorage.getItem('matricula')).then(function(response) {
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

