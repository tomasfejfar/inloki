using inloki.web.Models;
using inloki.web.ViewModels;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace inloki.web.Controllers
{
	public class AdminPathController : Controller
	{
		// GET: AdminPath
		public ActionResult Index()
		{
			return View(Database.Load().Paths);
		}

		// GET: AdminPath/Details/5
		public ActionResult Details(string name)
		{
			return View(Database.Load().Paths.Single(b => b.name == name));
		}

		// GET: AdminPath/Create
		public ActionResult Create()
		{
			var path = new PathViewModel
			{
				Beacons = (from b in Database.Load().Beacons
						   select new BeaconViewModel
						   {
							   Id = b.id,
							   Description = b.description,
							   Selected = true,
						   }).ToList(),
			};

			return View(path);
		}

		// POST: AdminPath/Create
		[HttpPost]
		public ActionResult Create(FormCollection collection)
		{
			try
			{
				var beaconSelections = collection["beacon"]
					.Replace("true,false", "true")
					.Split(',');

				var beacons = Database.Load().Beacons;
				var selectedWayPoints = from z in beacons.Zip(beaconSelections, (b, s) => new { b, s })
										where z.s == "true"
										select z.b.id;
				var path = new Path
				{
					name = collection["name"],
					description = collection["description"],
					waypoints = selectedWayPoints.ToList(),
				};

				Database.Modify(d => d.Paths.Add(path));

				return RedirectToAction("Index");
			}
			catch
			{
				return View();
			}
		}

		// GET: AdminPath/Edit/5
		public ActionResult Edit(string name)
		{
			var path = Database.Load().Paths.Single(p => p.name == name);

			var pathViewModel = new PathViewModel
			{
				name = path.name,
				description = path.description,
				Beacons = (from b in Database.Load().Beacons
						   let selected = path.waypoints.Contains(b.id)
						   select new BeaconViewModel
						   {
							   Id = b.id,
							   Description = b.description,
							   Selected = selected,
						   }).ToList(),
			};

			return View(pathViewModel);
		}

		// POST: AdminPath/Edit/5
		[HttpPost]
		public ActionResult Edit(string name, FormCollection collection)
		{
			try
			{
				Database.Modify(d =>
				{
					var path = d.Paths.Single(b => b.name == name);

					d.Paths.Remove(path);

					var beaconSelections = collection["beacon"]
						.Replace("true,false", "true")
						.Split(',');

					var beacons = Database.Load().Beacons;
					var selectedWayPoints = from z in beacons.Zip(beaconSelections, (b, s) => new { b, s })
											where z.s == "true"
											select z.b.id;
					path = new Path
					{
						name = collection["name"],
						description = collection["description"],
						waypoints = selectedWayPoints.ToList(),
					};

					d.Paths.Add(path);
				});

				return RedirectToAction("Index");
			}
			catch
			{
				return View();
			}
		}

		// GET: AdminPath/Delete/5
		public ActionResult Delete(string name)
		{
			return View(Database.Load().Paths.Single(b => b.name == name));
		}

		// POST: AdminPath/Delete/5
		[HttpPost]
		public ActionResult Delete(string name, FormCollection collection)
		{
			try
			{
				Database.Modify(d =>
				{
					var path = d.Paths.Single(b => b.name == name);

					d.Paths.Remove(path);
				});

				return RedirectToAction("Index");
			}
			catch
			{
				return View();
			}
		}
	}
}
