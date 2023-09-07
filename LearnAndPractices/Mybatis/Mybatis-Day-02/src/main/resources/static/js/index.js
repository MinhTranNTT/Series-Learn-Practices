const baseURL = "localhost:8080"

$("#uploadForm").submit(function (event) {
    event.preventDefault();

    const name = $('#name').val();
    const age = $('#age').val();
    const selectedImages = $('#imageUpload')[0].files;

    const formData = new FormData();
    formData.append('userName', name)
    formData.append('age', age)
    // selectImages.map(image => formData.append('images', image));
    /*selectedImages.map(function (image) {
        formData.append('images', image);
    });*/
    for (let i = 0; i < selectedImages.length; i++) {
        formData.append('images', selectedImages[i]);
    }

    axios.post(window.location.href + 'api/users', formData, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    }).then(res => {
        console.log(res);
    }).catch(err => {
        console.log(err);
    })
})