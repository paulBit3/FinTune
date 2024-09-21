import React, {useState} from 'react'


const User = () => {
  const [user, setUser] = useState({
    full_name: "",
    username: "",
    email: "",
    password: ""
  });

  const userData = {
    full_name,
    username,
    email,
    password
  };

  const handleChange = (e) => {
    const val = e.target.value;
    setUser({...user, [e.target.full_name]: val})
  };

  return (
    <div className="h-screen bg-gray-50">
      <Header />
      <Toaster position="top-center" />
      <div className="flex justify-center mt-16">
        <div className="border-2 rounded-2xl shadow-lg bg-white">
          <div className="grid grid-cols-2 w-[1200px]">
            <div className="">
              <Image
                className="rounded-l-2xl object-contain"
                src="https://img.freepik.com/free-photo/skyscrapers-looking-up-sky-modern-metropolis-modern-city_231208-7550.jpg?w=826&t=st=1675525974~exp=1675526574~hmac=f16043ac3b14cfb0c6afa1a32ed25ba1a7d3f695395819248563bd3d30d43b50"
                width={2000}
                height={100}
                alt=""
              />
            </div>

            <div className="">
              <img src="./bg.png" className="w-full h-full object-cover" />
            </div>
          </div>
        </div>

        <div className="absolute top-1/4 border bg-white p-10 rounded-2xl shadow-2xl z-10">
          <h3 className="text-4xl font-bold text-gray-600 pt-4 pb-6 text-center">
            Create an account
          </h3>

          <form
            className="w-fit sm:w-[400px] mt-5 mx-auto "
            onSubmit={handleSubmit(onSubmit)}
          >
            <div className="flex flex-col space-y-1">
            <label className="px-1 text-sm text-gray-500">Full Name</label>
              <input
                {...register("fullName", {
                  required: "Please enter your name.",
                })}
                className="formInput z-10"
                type="text"
                placeholder="Your Name"
              />
              <p className="text-[#ED6172] font-semibold px-2 text-start">
                {errors.fullName?.message}
              </p>
              <label className="px-1 pt-1 text-sm text-gray-500">Email address</label>
              <input
                {...register("email", {
                  required: "Please enter an email.",
                })}
                className="formInput"
                type="email"
                placeholder="example@example.com"
              />
              <p className="text-[#ED6172] font-semibold px-2 text-start">
                {errors.email?.message}
              </p>
              <label className="px-1 pt-1 text-sm text-gray-500">Password</label>
              <input
                {...register("password", {
                  required: "Please enter password.",
                })}
                className="formInput"
                type="password"
                placeholder="Enter password"
              />
              <p className="text-[#ED6172] font-semibold px-2 text-start">
                {errors.password?.message}
              </p>
            </div>
            <div className="text-center py-5 my-2">
              <button className="formButton" type="submit">
                Register
              </button>
            <p className="text-center pt-5">
              Already have an account?{" "}
              <Link href="/login">
                <span className="font-semibold underline decoration-teal-600 cursor-pointer">
                  Login!
                </span>
              </Link>
            </p>

            </div>
          </form>
        </div>
      </div>
    </div>
  )
}

export default User