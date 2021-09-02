
<template>
  <nav class="navbar navbar-default">
    <div class="container-fluid">
      <div class="navbar-header">
        <router-link to="/" class="navbar-brand">Pdf PoC</router-link>
      </div>

      <div class="collapse navbar-collapse">
        <ul class="nav navbar-nav">

          <li><router-link to="/" active-class="active">activeclass</router-link></li>
          <router-link to="/" tag="li" custom>HUHU</router-link>
          <router-link to="/" active-class="active" tag="li"><a>PdfWorld</a></router-link>
          <router-link to="/" active-class="active" tag="li"><a>PdfSplitJoin</a></router-link>
          <li><a href="http://localhost:8080">PdfSign</a></li>
          <router-link to="/" active-class="active" tag="li"><a>PdfFormFill</a></router-link>

        </ul>
      </div>
    </div>
  </nav>

</template>
