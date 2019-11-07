if((sessionStorage.getItem('level')) >=1 && (sessionStorage.getItem('level') <= 9)){
    sessionStorage.setItem('nivel', '0'+sessionStorage.getItem('level'));
} else {
    sessionStorage.setItem('nivel', sessionStorage.getItem('level'));
}

let progress = new Vue({
    el: '#levelJogador',
    data: {
        level: sessionStorage.getItem('nivel')
    },
    methods : {
    
    }
});
